# D9 Enterprise
A commercially supported ClojureScript platform by Degree9.

---

<p align="center">
  <a href="https://degree9.io" align="center">
    <img width="135" src="http://degree9.io/images/degree9.png">
  </a>
  <br>
  <b>D9 Enterprise is developed and maintained by Degree9</b>
</p>

---

This library is provided as an extension to [degree9/meta](http://github.com/degree9/meta).  
Where [meta] only provides the basic application server/client, D9 Enterprise provides a collection of services which can be mounted to server endpoints, as well as helpful service hooks and client components.

## Environment variables

This library supports nodejs process environment variables and [dotenv](https://github.com/motdotla/dotenv).

There is a convenience namespace `degree9.env` that inits dotenv and exposes the
native nodejs functionality.

See `.env.example` in the root of the repository for an example that can be
copied into place and updated with real secrets.

- `degree9.env/get`: Takes a key and optional fallback value, returns the value,
  fallback or empty string as appropriate. Accepts keyword and string keys in
  all casings. e.g.
  ```clojure
  (degree9.env/get :home) ; "/Users/foo"`
  (degree9.env/get "HOME") ; "/Users/foo"`
  ```
- `degree9.env/keys`: Returns the list of all available env map keywords.

## CLJS REPL

Using shadow cljs + node + REPL is the easiest way to develop without including
and rebuilding `enterprise` as an embedded dependency of some other repository.

We can't reliably use the browser REPL approach because some code must be
executed in node. For example, the Shopify API doesn't allow CORS, so browser
based POST is not an option.

Shadow CLJS is being used simply because it seemed to provide what we need and
I wasn't sure how to setup the equivalent through Boot.

All the REPL config for shadow is in `shadow-cljs.edn`.
The dependencies are being parsed from `shadow-cljs.edn` into `build.boot`.

The shadow CLJS docs are pretty good:

https://shadow-cljs.github.io/docs/UsersGuide.html

The default shadow cljs config is for `app` and targets `node-script`, but also
can support the `browser` target too (the config covers both options).

### Install shadow CLJS

Shadow CLJS is a dev dependency for npm in this repo.

It might be easier to install it globally though:

`$ npm install -g shadow-cljs`

### Shadow CLJS + Node + REPL

First run `watch` to compile the needed JS file.

`$ shadow-cljs watch app`

This will start a REPL server, watch files and output to `repl-node/main.js`.

Next, in a new terminal tab, run the compiled file with node.

`$ node repl-node/main.js`

This will provide the execution context for the REPL, as a browser normally
would. All `prn` and console logs will show up here.

Finally, in a new terminal tab, start the CLJS REPL connected to the server.

`$ shadow-cljs cljs-repl app`

This allows for executing cljs code in the node execution environment.

Note that the `watch` command from shadow cljs WILL reload files dynamically but
does NOT automatically reload the _state_ of namespaces from the perspective of
the REPL.

To reload an already-loaded namespace, use `:reload` or `:reload-all`.

For example:

`(require 'repl.start :reload)`

Would allow edits to `repl.start/foo` to appear in the REPL client without
needing to stop and start the REPL server.

### Shadow CLJS + Browser + REPL

Edit `shadow-cljs.edn` so that the `:target` for `:app` is `:browser`.

Run the watch command:

`$ shadow-cljs watch app`

This will output compiled browser-friendly JS files to `repl-public/js/main.js`.

A web server will also be available at `http://localhost:8020`.

Open a browser (e.g. Chrome) to the localhost web server URL. All `prn` and
console logs will show up here.

In a new terminal tab, start the browser REPL.

`$ shadow-cljs browser-repl app`

Be careful not to refresh the browser as it can mess with the state of the REPL.

To reload namespaces, see the node instructions above.

### Shadow CLJS + Testing

Shadow CLJS runs tests for us too, but uses the `test` build rather than `app`.

The node test runner in shadow CLJS does NOT need a standalone node instance
running as it is configured with `:autorun`.

`$ shadow-cljs compile test`

This only runs once though. The `watch` tool can help, install on Mac OS with
`brew install watch` and wrap shadow CLJS with it.

`$ watch shadow-cljs compile test`
