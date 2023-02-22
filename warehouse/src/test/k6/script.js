import http from 'k6/http';
import {check} from 'k6';

export default function() {
    let res = http.get('http://warehouse:8081/api/v1/warehouse/datasource');
    check(res, { 'is status 200': (r) => r.status === 200 });
}