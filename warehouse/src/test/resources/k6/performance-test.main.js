import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js'

export {list100FoodsUC} from './use_cases/list_100_foods_uc.js';
export {list1000FoodsUC} from './use_cases/list_1000_foods_uc.js';
export {list10000FoodsUC} from './use_cases/list_10000_foods_uc.js';

export let options = {
    insecureSkipTLSVerify: true,
    discardResponseBodies: true,
    hosts: {
        'warehouse': '127.0.0.1'
    },
    thresholds: {
        "list_100_foods_uc_duration":["avg<=1000"],
        "list_1000_foods_uc_duration":["avg<=2000"],
        "list_10000_foods_uc_duration":["avg<=3000"]
    },
    scenarios: {
        "list_100_foods_scenario": {
            "exec": "list100FoodsUC",
            "executor": "constant-arrival-rate",
            "startTime": "0s",
            duration: '30s',
            "timeUnit": "1s",
            rate: 50,
            preAllocatedVUs: 50,
            maxVUs: 100
        },
        "list_1000_foods_scenario": {
            "exec": "list1000FoodsUC",
            "executor": "constant-arrival-rate",
            "startTime": "30s",
            duration: '30s',
            "timeUnit": "1s",
            rate: 50,
            preAllocatedVUs: 50,
            maxVUs: 100
        },
        "list_10000_foods_scenario": {
            "exec": "list10000FoodsUC",
            "executor": "constant-arrival-rate",
            "startTime": "1m",
            duration: '30s',
            "timeUnit": "1s",
            rate: 50,
            preAllocatedVUs: 50,
            maxVUs: 100
        }
    }
}

//main method that drives the test
export default function(){
}

//handleSummary produces the reports
export function handleSummary(data){
    console.log('Preparing the end-of-test summary...');

    return {
        'stdout': textSummary(data, {indent: ' ', enableColors: true})
    }
}