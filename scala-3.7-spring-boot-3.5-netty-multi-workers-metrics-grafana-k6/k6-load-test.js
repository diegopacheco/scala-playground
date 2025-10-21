import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 20 },
    { duration: '1m', target: 50 },
    { duration: '30s', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'],
    http_req_failed: ['rate<0.1'],
  },
};

export default function () {
  const fastEndpoint = http.get('http://localhost:8081/');
  check(fastEndpoint, {
    'fast endpoint status is 200': (r) => r.status === 200,
  });

  const createEntries = http.get('http://localhost:8081/create/3');
  check(createEntries, {
    'create entries status is 200': (r) => r.status === 200,
  });

  const retrieve = http.get('http://localhost:8081/retrieve');
  check(retrieve, {
    'retrieve status is 200': (r) => r.status === 200,
  });

  sleep(1);
}
