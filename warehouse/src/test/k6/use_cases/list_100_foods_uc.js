import http from 'k6/http';
import {check, group} from 'k6';
import { Trend } from 'k6/metrics';

const list100FoodsUCDuration = new Trend('list_100_foods_uc_duration')

export function list100FoodsUC() {
    group("List 100 foods", function() {
        let res = http.get('http://warehouse:8081/api/v1/warehouse/foods?limit=100');
        check(res, {
            'is status 200': (r) => r.status === 200
        });

        list100FoodsUCDuration.add(res.timings.duration);
    })
}