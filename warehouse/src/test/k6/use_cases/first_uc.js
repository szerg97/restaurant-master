import http from 'k6/http';
import {check} from 'k6';
import { Trend } from 'k6/metrics';

const firstUCDuration = new Trend('first_uc_duration')

export function firstUC() {
    let res = http.get('http://warehouse:8081/api/v1/warehouse/tables');
    check(res, { 'is status 200': (r) => r.status === 200 });
    firstUCDuration.add(res.timings.duration);
}