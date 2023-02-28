import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js'

export {firstUC} from './use_cases/first_uc.js';

export let options = {
    insecureSkipTLSVerify: true,
    hosts: {
        'warehouse': '127.0.0.1'
    },
    thresholds: {
        "first_uc_duration":["max<3000"]
    },
    scenarios: {
        "first_scenario": {
            "exec": "firstUC",
            "executor": "ramping-arrival-rate",
            "startTime": "0s",
            "timeUnit": "1s",
            preAllocatedVUs: 10,
            maxVUs: 50,
            "stages": [
                { "target": 10, "duration": "15s" },
                { "target": 50, "duration": "35s" },
                { "target": 0, "duration": "10s" }
            ]
        }
    }
}

//main method that drives the test
export default function(){
    firstUC();
}

//handleSummary produces the reports
export function handleSummary(data){
    console.log('Preparing the end-of-test summary...');

    return {
        'stdout': textSummary(data, {indent: ' ', enableColors: true})
    }
}