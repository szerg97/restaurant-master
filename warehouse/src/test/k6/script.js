import http from 'k6/http';
import {check} from 'k6';

export default function() {
    let res = http.get('http://backend:8081/api/v1/warehouse/meal');
    check(res, { 'is status 200': (r) => r.status === 200 });
}