[![Continuous Integration](https://github.com/SmartWasteCollection/complaint-microservice/actions/workflows/ci.yml/badge.svg?event=push)](https://github.com/SmartWasteCollection/complaint-microservice/actions/workflows/ci.yml)
[![Continuous Integration](https://github.com/SmartWasteCollection/complaint-microservice/actions/workflows/cd.yml/badge.svg?event=push)](https://github.com/SmartWasteCollection/complaint-microservice/actions/workflows/cd.yml)
[![GitHub issues](https://img.shields.io/github/issues-raw/SmartWasteCollection/complaint-microservice?style=plastic)](https://github.com/SmartWasteCollection/complaint-microservice/issues)
[![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/SmartWasteCollection/complaint-microservice?style=plastic)](https://github.com/SmartWasteCollection/complaint-microservice/pulls)
[![GitHub](https://img.shields.io/github/license/SmartWasteCollection/complaint-microservice?style=plastic)](/LICENSE)
[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/SmartWasteCollection/complaint-microservice?include_prereleases&style=plastic)](https://github.com/SmartWasteCollection/complaint-microservice/releases)
[![codecov](https://codecov.io/gh/SmartWasteCollection/complaint-microservice/branch/main/graph/badge.svg?token=DFXD6WEUFK)](https://codecov.io/gh/SmartWasteCollection/complaint-microservice)

# complaint-microservice

This repository contains the microservice that handles the generation and management of complaints.

---

To run this microservice you can get the system's latest container image:

```
$ docker run -p 3000:<port> -e MONGO_CONNECTION_STRING=<connection-string> ghcr.io/smartwastecollection/complaint-microservice:<latestVersion>
```
