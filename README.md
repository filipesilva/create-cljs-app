# Create CLJS App [![Build Status](https://github.com/filipesilva/create-cljs-app/workflows/Node%20CI/badge.svg?branch=master)](https://github.com/filipesilva/create-cljs-app/actions)

Create ClojureScript apps with a single command.<br>
It is focused on making it easy for JS and React developers to get started with ClojureScript.

Create CLJS App works on Windows, Linux, and macOS.<br>
If something doesn’t work, please [file an issue](https://github.com/filipesilva/create-cljs-app/issues/new).<br>

Heavily inspired on [create-react-app](https://github.com/facebook/create-react-app). A lot of messages are mostly the same for now (create-react-app maintainers: if that's not ok, let me know).

## Quick Overview

```sh
npx create-cljs-app my-app
cd my-app
npm start
```

Then open [http://localhost:3000/](http://localhost:3000/) to see your app.<br>
When you’re ready to deploy to production, create a minified bundle with `npm run build`.

## Creating an App

**You’ll need to have Node 10.16.0 or later version on your local development machine** (but it’s not required on the server). You can use [nvm](https://github.com/creationix/nvm#installation) (macOS/Linux) or [nvm-windows](https://github.com/coreybutler/nvm-windows#node-version-manager-nvm-for-windows) to easily switch Node versions between different projects.

**You'll also need a [Java SDK](https://adoptopenjdk.net/) (Version 8+, Hotspot).**

To create a new app, you may choose one of the following methods:

### npx

```sh
npx create-cljs-app my-app
```
_([npx](https://medium.com/@maybekatz/introducing-npx-an-npm-package-runner-55f7d4bd282b) comes with npm 5.2+ and higher)_

### npm

```sh
npm init cljs-app my-app
```

_`npm init <initializer>` is available in npm 6+_

### Yarn

```sh
yarn create cljs-app my-app
```

_`yarn create` is available in Yarn 0.25+_

It will create a directory called `my-app` inside the current folder.<br>
Inside that directory, it will generate the initial project structure and install the transitive dependencies:

```
my-app
├── README.md
├── package.json
├── node_modules
├── shadow-cljs.edn
├── .gitignore
├── public
|  ├── css
|  |  └── style.css
|  ├── index.html
|  └── favicon.ico
└── src
   ├── app
   |  ├── cards
   |  |  ├── devcards_runner.cljs
   |  |  ├── helpers.cljs        
   |  |  └── test_runner.cljs    
   |  ├── core.cljs
   |  ├── hello.cljs
   |  └── hello_cards.cljs       
   └── e2e
      └── core.cljs
```

Once the installation is done, you can open your project folder:

```sh
cd my-app
```

Inside the newly created project, you can run some built-in commands:

### `npm start` or `yarn start`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.

The app uses [Reagent](https://reagent-project.github.io), a minimalistic interface between ClojureScript and React.<br>
You can use existing npm React components directly via a [interop call](http://reagent-project.github.io/docs/master/InteropWithReact.html#creating-reagent-components-from-react-components).

Builds use [Shadow CLJS](https://github.com/thheller/shadow-cljs) for maximum compatibility with NPM libraries. You'll need a [Java SDK](https://adoptopenjdk.net/) (Version 8+, Hotspot) to use it. <br>
You can [import npm libraries](https://shadow-cljs.github.io/docs/UsersGuide.html#js-deps) using Shadow CLJS. See the [user manual](https://shadow-cljs.github.io/docs/UsersGuide.html) for more information.

### `npm run cards` or `yarn cards`

Runs the interactive live development enviroment.<br>
You can use it to design, test, and think about parts of your app in isolation.

This environment uses [Devcards](https://github.com/bhauman/devcards) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro).

### `npm run build` or `yarn build`

Builds the app for production to the `public` folder.<br>
It correctly bundles all code and optimizes the build for the best performance.

### `npm test` or `yarn test`, and `npm run e2e` or `yarn e2e`

`test` launches the test runner in the interactive watch mode.<br>
You can use `test:once` instead to run the tests a single time, and `e2e` to run end-to-end tests.

See the ClojureScript [testing page](https://clojurescript.org/tools/testing) for more information. E2E tests use [Taiko](https://github.com/getgauge/taiko) to interact with a headless browser.

### `npm run lint` or `yarn lint`, and `npm run format` or `yarn format`

`lint` checks the code for known bad code patterns using [clj-kondo](https://github.com/borkdude/clj-kondo).

`format` will format your code in a consistent manner using [zprint-clj](https://github.com/clj-commons/zprint-clj).

### `npm run report` or `yarn report`

Make a report of what files contribute to your app size.<br>
Consider [code-splitting](https://code.thheller.com/blog/shadow-cljs/2019/03/03/code-splitting-clojurescript.html) or using smaller libraries to make your app load faster.

### `npm run server` or `yarn server`

Starts a Shadow CLJS background server.<br>
This will speed up starting time for other commands that use Shadow CLJS.

## License

Create CLJS App is open source software [licensed as MIT](https://github.com/filipesilva/create-cljs-app/blob/master/LICENSE).