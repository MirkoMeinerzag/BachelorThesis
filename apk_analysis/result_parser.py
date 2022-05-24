# This file reads the output from apk_analyzer(results.txt)
import json
result = {
        "allowTaskReparenting": 0,
        "launchMode": 0,
        "singleTop": 0,
        "singleTask": 0,
        "singleInstance": 0,
        "singleInstancePerTask": 0,
        "ownTaskAffinity": 0,
        "taskAffinity": 0,
        "emptyTaskAffinity": 0,
        "excludeFromRecents": 0,
        "alwaysRetainTaskState": 0,
        "total": 0,
        "taskAffinities" : {}
    }
def digestResults():
    
    
    with open("results.txt", "r") as file:
        for line in file.readlines():
            apk_data = json.loads(line)
            result["total"] += 1
            for key in apk_data:
                if key == "Package":
                    print("Evaluating " + apk_data[key])
                    continue
                if key == "taskAffinities":
                    for affinity in apk_data[key]:
                        if affinity in result[key].keys():
                            result[key][affinity] += 1
                        else:
                            result[key][affinity] = 1
                    continue
                result[key] += 1
    result["taskAffinities"] = sorted(result["taskAffinities"].items(), key=lambda x: x[1], reverse=True)    
    with open("evaluatedResult.json", "w") as evalFile:
        json.dump(result, evalFile, indent=4)


if __name__ == "__main__":
    digestResults()
