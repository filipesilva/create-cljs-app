This project was bootstrapped with [Create CLJS App](https://github.com/filipesilva/create-cljs-app).

## Available Scripts

In the project directory, you can run:

### `yarn start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.

Builds use [ShadowCLJS](https://github.com/thheller/shadow-cljs) for maximum compatibility with NPM libraries. You'll need a [Java SDK](https://adoptopenjdk.net/) (Version 8+, Hotspot) to use it. <br />
See the [Shadow CLJS user manual](https://shadow-cljs.github.io/docs/UsersGuide.html) for more information.

### `yarn test`

Launches the test runner in the interactive watch mode.<br />
You can use `yarn test:once` to run the tests a single time, and `yarn e2e` to run end-to-end tests.

See the ClojureScript [testing page](https://clojurescript.org/tools/testing) for more information. E2E tests use [Taiko](https://github.com/getgauge/taiko) to interact with a headless browser.

### `yarn lint`

Lint code for known bad code patterns.

See the [clj-kondo](https://github.com/borkdude/clj-kondo) for more information.

Note: does not work on Windows currently, see this [issue](https://github.com/filipesilva/create-cljs-app/issues/2) for a workaround.

### `yarn build`

Builds the app for production to the `public` folder.<br />
It correctly bundles all code and optimizes the build for the best performance.

Your app is ready to be deployed!

## Useful resources

Official CLJS API https://cljs.github.io/api/.

Quick reference https://cljs.info/cheatsheet/.

Offline searchable docs https://devdocs.io/.

VSCode plugin https://github.com/BetterThanTomorrow/calva.

