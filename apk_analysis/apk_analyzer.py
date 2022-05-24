from androguard.core.bytecodes.apk import APK
import multiprocessing as mp
import os
import json

androidNamespace = "{http://schemas.android.com/apk/res/android}"
launchModes = {
    "0": "standard",
    "1": "singleTop",
    "2": "singleTask",
    "3": "singleInstance",
    "4": "singleInstancePerTask"
}
def crawl_apks(folderName, queue):
    print("Crawling " + folderName + " for apks.")
    count = 0
    for root, _, files in os.walk("./" + folderName):
        for file in files:
            try:
                if(file.lower().endswith('.apk')):
                    count += 1
                    filePath = root + "/" + file
                    print("Found ", count, ". apk at " + filePath)
                    queue.put(filePath)
            except Exception as  e:
                # To-Do: Write error file to see which apks could not be analyzed and why
                print("Error while analysing apk " + file + " \nError: ", e)
    print("Finished crawling folders.")
    # May-Do: Print results of analysis to console


def analyse(queue, dataLock):
    while queue.empty() == False:
        filePath = ""
        try:
            filePath = queue.get(1)
        except TimeoutError as terr:
            print("TimeOut")
        apk = APK(filePath)
        packageName = apk.get_package()
        print(os.getpid(), " analysing " + packageName)
        axmlPrinter = apk.get_android_manifest_axml()
        xml = axmlPrinter.get_xml_obj()
        application = xml.find("application")
        data = {
            "Package": packageName,
            "taskAffinities": set()
        }
        for activity in application.iter("activity"):
            # first check if we have allowTaskReparenting set to true
            allowTaskReparenting = activity.get(androidNamespace + "allowTaskReparenting")
            if(allowTaskReparenting == "true"):
                data["allowTaskReparenting"] = True
            
            # next launchMode is analyzed
            launchMode = activity.get(androidNamespace + "launchMode")
            if(launchMode != None):
                # now we check if launchMode is not default
                if(launchMode != "0"):
                    data["launchMode"] = True
                    launchMode = launchModes[launchMode]
                    if launchMode in data:
                        data[launchMode] = data[launchMode] + 1
                    else:
                        data[launchMode] = 1
            
            # taskAffinity
            taskAffinity = activity.get(androidNamespace + "taskAffinity")
            if(taskAffinity != None):
                if(taskAffinity == packageName):
                    data["ownTaskAffinity"] = True
                else:
                    if(taskAffinity == ""):
                        data["emptyTaskAffinity"] = True
                    else:
                        data["taskAffinity"] = True
                    data["taskAffinities"].add(taskAffinity)

            # excludeFromRecents 
            excludeFromRecents = activity.get(androidNamespace + "excludeFromRecents")
            if(excludeFromRecents == "true"):
                data["excludeFromRecents"] = True

            # alwaysRetainTaskState
            alwaysRetainTaskState = activity.get(androidNamespace + "alwaysRetainTaskState")
            if(alwaysRetainTaskState == "true"):
                data["alwaysRetainTaskState"] = True
        writeResults(data, dataLock)
        print("Finished analyzing " + packageName + ". Result: ", data)
    print("Worker finished")

def writeResults(data, lock):
    data = json.dumps(data, default=serialize_sets)
    try:
        lock.acquire()
        print("Writing to file")
        resultFile = open("results.txt", "a")
        
        resultFile.write(data)
        resultFile.write("\n")
        resultFile.close()
    finally:
        lock.release()
        #write to file

def serialize_sets(obj):
    if isinstance(obj, set):
        return list(obj)

    return obj


if __name__ == '__main__':
    apkFileQueue = mp.Queue()
    dataLock = mp.Lock()
    crawlerProcess = mp.Process(target=crawl_apks, args=('dataset', apkFileQueue,))
    crawlerProcess.start()
    analyzerPool = mp.Pool(16, analyse, (apkFileQueue, dataLock,))
    crawlerProcess.join()
    print("Finished")
    #crawl_apks("dataset", 8)
#print(analyse("./dataset/mallory.apk"))
