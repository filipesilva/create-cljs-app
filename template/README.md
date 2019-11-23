This project was bootstrapped with [Create CLJS App](https://github.com/filipesilva/create-cljs-app).

## Available Scripts

In the project directory, you can run:

### `__START__`

Runs the app in development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.

The app uses [Reagent](https://reagent-project.github.io), a minimalistic interface between ClojureScript and React.<br>
You can use existing npm React components directly via a [interop call](http://reagent-project.github.io/docs/master/InteropWithReact.html#creating-reagent-components-from-react-components).

Builds use [Shadow CLJS](https://github.com/thheller/shadow-cljs) for maximum compatibility with NPM libraries. You'll need a [Java SDK](https://adoptopenjdk.net/) (Version 8+, Hotspot) to use it. <br>
You can [import npm libraries](https://shadow-cljs.github.io/docs/UsersGuide.html#js-deps) using Shadow CLJS. See the [user manual](https://shadow-cljs.github.io/docs/UsersGuide.html) for more information.

### `__CARDS__`

Runs the interactive live development enviroment.<br>
You can use it to design, test, and think about parts of your app in isolation.

This environment uses [Devcards](https://github.com/bhauman/devcards) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro).

### `__TEST__` and `__E2E__`

You can use `__TEST__` to run tests a single time, and `__E2E__` to run the end-to-end test app.
`__TEST:WATCH__` launches tests in interactive watch mode.<br>

See the ClojureScript [testing page](https://clojurescript.org/tools/testing) for more information. E2E tests use [Taiko](https://github.com/getgauge/taiko) to interact with a headless browser.

### `__LINT__` and `__FORMAT__`

`__LINT__` checks the code for known bad code patterns using [clj-kondo](https://github.com/borkdude/clj-kondo).

`__FORMAT__` will format your code in a consistent manner using [zprint-clj](https://github.com/clj-commons/zprint-clj).

### `__BUILD__`

Builds the app for production to the `public` folder.<br>
It correctly bundles all code and optimizes the build for the best performance.

Your app is ready to be deployed!

### `__SERVER__`

Starts a Shadow CLJS background server.<br>
This will speed up starting time for other commands that use Shadow CLJS.

## Useful resources

Clojurians Slack http://clojurians.net/.

CLJS FAQ (for JavaScript developers) https://clojurescript.org/guides/faq-js.

Official CLJS API https://cljs.github.io/api/.

Quick reference https://cljs.info/cheatsheet/.

Offline searchable docs https://devdocs.io/.

VSCode plugin https://github.com/BetterThanTomorrow/calva.

