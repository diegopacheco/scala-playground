import http from 'k6/http';
import { check, sleep } from 'k6';
export let options = {
  vus: __ENV.VUS || 10,
  duration: __ENV.DURATION || '30s',
};
export default function () {
  let response = http.get('http://localhost:8081/retrieve');
  check(response, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
