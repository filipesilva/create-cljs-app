This project was bootstrapped with [Create CLJS App](https://github.com/filipesilva/create-cljs-app).

## Available Scripts

In the project directory, you can run:

### `__START__`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.

The app uses [Reagent](https://reagent-project.github.io), a minimalistic interface between ClojureScript and React.<br>
You can use existing npm React components directly via a [interop call](http://reagent-project.github.io/docs/master/InteropWithReact.html#creating-reagent-components-from-react-components).

Builds use [Shadow CLJS](https://github.com/thheller/shadow-cljs) for maximum compatibility with NPM libraries. You'll need a [Java SDK](https://adoptopenjdk.net/) (Version 8+, Hotspot) to use it. <br>
You can [import npm libraries](https://shadow-cljs.github.io/docs/UsersGuide.html#js-deps) using Shadow CLJS. See the [user manual](https://shadow-cljs.github.io/docs/UsersGuide.html) for more information.

### `__TEST__` and `__E2E__`

`__TEST__` launches the test runner in the interactive watch mode.<br>
You can use `__TEST:ONCE__` to run the tests a single time, and `__E2E__` to run end-to-end tests.

See the ClojureScript [testing page](https://clojurescript.org/tools/testing) for more information. E2E tests use [Taiko](https://github.com/getgauge/taiko) to interact with a headless browser.

### `__LINT__` and `__FORMAT__`

`__LINT__` checks the code for known bad code patterns using [clj-kondo](https://github.com/borkdude/clj-kondo).<br>
Note: does not work on Windows currently, see this [issue](https://github.com/filipesilva/create-cljs-app/issues/2) for a workaround.

`__FORMAT__` will format your code in a consistent manner using [zprint-clj](https://github.com/clj-commons/zprint-clj).

### `__BUILD__`

Builds the app for production to the `public` folder.<br>
It correctly bundles all code and optimizes the build for the best performance.

Your app is ready to be deployed!

## Useful resources

Official CLJS API https://cljs.github.io/api/.

Quick reference https://cljs.info/cheatsheet/.

Offline searchable docs https://devdocs.io/.

VSCode plugin https://github.com/BetterThanTomorrow/calva.

