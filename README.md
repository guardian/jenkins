# Atom Renderer

A library that renders atoms from the content API into front-end code. It is written in
javascript using type-checking from FlowJS, and wrapped in a Scala app. 

## To Install 

1. Clone repo: `git clone git@github.com:guardian/atom-renderer.git`

2. Install node dependencies: `yarn`

3. Install sbt dependencies `sbt compile`. You need to have the [pgp plugin installed globally](https://www.scala-sbt.org/sbt-pgp/index.html).

## Run FlowJS

Installing the node modules above should have installed what you need to run flow. 

To check for any compile errors, run flow like this: `yarn run flow`

More information on [Getting Started with Flow](https://flow.org/en/docs/getting-started/). 

## Build the app 

1. Build the JS code: `yarn frontend` & `yarn apps`

2. Build the Scala code: `sbt compile` 

## Preview your changes

### Locally

The `utils` project provides a convenience application to quickly preview the rendering of an atom. Just go in that project and `run` it, then hit http://localhost:9000. The app will ask you to select an atom type and type an atom ID, then will render it using the "article" rendering.

### In Frontend

You need to publish your changes to NPM as a new version of the Atom Renderer package. Then, you need to open
the Guardian's Frontend app on your local machine, require the updated Atom Renderer package 
and run Frontend. 

*In Atom-Renderer:*

1. Update `version` in `package.json` to something like `X.X.X-yourname.test.1` Use the [semver 
convention](http://nodesource.com/blog/semver-a-primer/) to increment the version number. The '-yourname.test.1' part on the end indicates that this is a version to be used for testing purposes, not for actual use.). Commit these changes. 

2. Build the frontend: `yarn frontend` 

3. Build the apps: `yarn apps`

4. Make sure you a) have an account on NPMjs. and b) it is added to the Guardian group on npm. 
Ensure your machine is logged into this account. If not, run: `npm adduser` and sign in 

5. Publish your version to NPM:  `npm publish`
The version you just pushed should appear here: https://www.npmjs.com/package/@guardian/atom-renderer

6. In the (highly likely) scenario that you've made Scala code changes, that you'll want to test in Frontend (see below) you'll also need to publish your Scala changes locally.
First, **check that `atom-renderer/version.sbt` is correct**, i.e. make sure it ends with `-SNAPSHOT`, then run `sbt +publishLocal` (the `+` is significant for cross-compilation of Scala versions - see `project/Dependencies.scala` for those) to add the updated library files to your local `.ivy2/local/com.gu/` cache. 
This'll be tagged with the same version as shown in `atom-renderer/version.sbt`.

*In Frontend*

1. In a new window open up Frontend. You need to have the [Frontend app](https://github.com/guardian/frontend) installed and set up with AWS credentials 
from Janus added.
See how to do that [here](https://github.com/guardian/frontend/blob/master/docs/01-start-here/01-installation-steps.md). 

2. In a local branch, tell the app to use the latest version of the atom-renderer:

`yarn add @guardian/atom-renderer@X.X.X-yourname.test.1 -W`

3. Recompile the frontend with the new version of Atom Renderer added: `make compile-dev`

4. Run the frontend code: `make watch`

5. Open new terminal and run:  `./sbt`

6. Switch into the article project, this will let you see the atom page: `project article`

7. Run it:  `run`

8. You should be able to preview all atoms on this page: http://localhost:9000/open-platform/blog/related-content 
Here live: https://www.theguardian.com/open-platform/blog/related-content


## To Deploy

1. Publish the library to NPM 

2. Publish the library to Maven

3. Update Frontend to reference the new versions. 


*To publish to Maven*

1. Open the sbt interactive shell: `sbt`

2. Compile the code `compile`

3. Release `release`

4. Once the version has [updated on Maven](https://search.maven.org/#search%7Cga%7C1%7Catom-renderer), 
go to the Frontend app, find the Dependencies.scala file and update the version number for Atom-Renderer. Run
`./sbt` to open the interactive console and then `compile` to pull in the new dependency. 


## Adding a new Atom? 

Make sure to add your new atom type in these two files in Atom-Renderer:

* `core/src/main/resources/__flow__/types/atoms.fjs`

* `webpack/atomTypes.js` 

*Contact us in #journalism-team if you have problems with the repo / readme.* 

--

\* Yarn is used in these instructions, but you can use npm if you prefer 

