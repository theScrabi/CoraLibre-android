# CoraLibre – Open Source version of the German Corona-Warn-App

**Note:** This app is not ready for any meaningful usage yet.

This app is a fork of the [official Corona-Warn-App](https://github.com/corona-warn-app/cwa-app-android).
We're trying to keep the CoraLibre app close to the official app and frequently incorporate upstream
changes. The differences should be limited to branding and the replacement of the
Google Play Services, other changes should be made in the upstream repository.

## Working with the repository

This repo uses [git submodules](https://git-scm.com/docs/gitsubmodules) to include the
[CoraLibre-android-sdk](https://github.com/CoraLibre/CoraLibre-android-sdk). To get it to properly
work, you need to clone the repo using the `--recurse-submodules` flag:

```sh
git clone --recurse-submodules git@github.com:CoraLibre/CoraLibre-android.git
```

If you already cloned the repo without the above flag:

```sh
git submodule update --init
```

If your local submodule content is outdated:

```sh
git submodule update
```

## Contribution

Anyone is welcome to contribute to this project. To do so, please fork this repository and create
a new branch based off the `master` branch. Commit your changes to that new branch and open a new
pull request targeting the `master` branch again. As explained above, the changes to the app should
be very limited to maintain good mergeability with upstream. Be sure to open an issue and –
especially for bigger changes – discuss the proposed changes before implementing something that
won't be merged later.

Feel free to join us on our [public Matrix channel](https://matrix.to/#/!ytowiHTGWVFzpoihuQ:matrix.org), where
most discussions take place.

## Syncing with upstream

Upstream changes are regularly incorporated using git merges. The `master` branch of this repo
tracks the `master` branch of the upstream repository, because that branch contains the most stable
code and should generally always be tested for release by SAP.

## Licensing

Copyright (c) 2020 SAP SE or an SAP affiliate company.

Licensed under the **Apache License, Version 2.0** (the "License"); you may not use this file except in compliance with the License.

You may obtain a copy of the License at https://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the [LICENSE](./LICENSE) for the specific language governing permissions and limitations under the License.
