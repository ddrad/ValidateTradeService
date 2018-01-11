const PROXY_CONFIG = [
  {
    context: [
      "/app-validator",
      "/app-auth"
    ],
    target: "http://localhost:8090",
    secure: false
  }
];

module.exports = PROXY_CONFIG;
