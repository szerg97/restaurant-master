import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js'

export {list100FoodsUC} from './use_cases/list_100_foods_uc.js';
export {list1000FoodsUC} from './use_cases/list_1000_foods_uc.js';
export {list10000FoodsUC} from './use_cases/list_10000_foods_uc.js';

export let options = {
    insecureSkipTLSVerify: true,
    hosts: {
        'warehouse': '127.0.0.1'
    },
    thresholds: {
        "list_100_foods_uc_duration":["max<3000"],
        "list_1000_foods_uc_duration":["max<3000"],
        "list_10000_foods_uc_duration":["max<3000"]
    },
    scenarios: {
        "list_100_foods_scenario": {
            "exec": "list100FoodsUC",
            "executor": "ramping-arrival-rate",
            "startTime": "0s",
            "timeUnit": "1s",
            preAllocatedVUs: 10,
            maxVUs: 50,
            stages: [
                { "target": 10, "duration": "15s" },
                { "target": 50, "duration": "35s" },
                { "target": 0, "duration": "10s" }
            ]
        },
        "list_1000_foods_scenario": {
            "exec": "list1000FoodsUC",
            "executor": "ramping-arrival-rate",
            "startTime": "1m",
            "timeUnit": "1s",
            preAllocatedVUs: 10,
            maxVUs: 50,
            stages: [
                { "target": 10, "duration": "15s" },
                { "target": 50, "duration": "35s" },
                { "target": 0, "duration": "10s" }
            ]
        },
        "list_10000_foods_scenario": {
            "exec": "list10000FoodsUC",
            "executor": "ramping-arrival-rate",
            "startTime": "2m",
            "timeUnit": "1s",
            preAllocatedVUs: 10,
            maxVUs: 50,
            stages: [
                { "target": 10, "duration": "15s" },
                { "target": 50, "duration": "35s" },
                { "target": 0, "duration": "10s" }
            ]
        }
    }
}

//main method that drives the test
export default function(){
    list100FoodsUC();
}

//handleSummary produces the reports
export function handleSummary(data){
    console.log('Preparing the end-of-test summary...');

    return {
        'stdout': textSummary(data, {indent: ' ', enableColors: true})
    }
}