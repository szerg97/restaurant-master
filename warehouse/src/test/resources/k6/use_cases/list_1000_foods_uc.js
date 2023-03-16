import http from 'k6/http';
import {check, group} from 'k6';
import { Trend } from 'k6/metrics';

const list1000FoodsUCDuration = new Trend('list_1000_foods_uc_duration')

export function list1000FoodsUC() {
    group("List 1000 foods", function() {
        let res = http.get('http://warehouse:8081/api/v1/warehouse/foods?limit=1000');
        check(res, {
            'is status 200': (r) => r.status === 200
        });

        list1000FoodsUCDuration.add(res.timings.duration);
    })
}