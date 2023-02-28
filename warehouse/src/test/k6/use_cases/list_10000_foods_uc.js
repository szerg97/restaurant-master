import http from 'k6/http';
import {check, group} from 'k6';
import { Trend } from 'k6/metrics';

const list10000FoodsUCDuration = new Trend('list_10000_foods_uc_duration')

export function list10000FoodsUC() {
    group("List 10000 foods", function() {
        let res = http.get('http://warehouse:8081/api/v1/warehouse/foods?limit=10000');
        check(res, {
            'is status 200': (r) => r.status === 200
        });

        list10000FoodsUCDuration.add(res.timings.duration);
    })
}