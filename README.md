# Atom Renderer

A library that renders atoms from the content API into front-end code. It is written in
javascript using type-checking from FlowJS, and wrapped in a Scala app. 

## To Install 

1. Clone repo: `git clone git@github.com:guardian/atom-renderer.git`

2. Install node dependencies: `yarn`*

3. Install sbt dependencies `sbt compile`

## Run FlowJS

Installing the node modules above should have installed what you need to run flow. 

To check for any compile errors, run flow like this: `yarn run flow`

More information on [Getting Started with Flow](https://flow.org/en/docs/getting-started/). 

## Build the app 

1. Build the JS code: `yarn build`

2. Build the Scala code: `sbt compile` 

## Preview your changes

In future we may add a dummy atom so the results of your change can be previewed from within the repo. 

For now, you need to publish your changes to NPM as a new version of the Atom Renderer package. Then, you need to open
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

* Publish the library to NPM 

* Publish the library to Maven

Update Frontend to reference the new versions. 

*Contact us in #journalism-team if you have problems with the repo / readme.* 

--

\* Yarn is used in these instructions, but you can use npm if you prefer 

