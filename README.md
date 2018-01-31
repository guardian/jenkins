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

1. Build the JS code: `yarn frontend` & `yarn apps`

2. Build the Scala code: `sbt compile` 

## Preview your changes

### Locally

The `utils` project provides a convenience tool for (_ahem_) quickly testing your changes. In order to use it,
you will need your API key. With that in hand, just run the SBT console:

```
% sbt
> project utils
> console
import monix.execution.Scheduler.Implicits.global
import com.gu.contentatom.thrift._
import com.gu.contentatom.renderer._
import com.gu.contentatom.renderer.utils._
**scala>** val client = IoCapiRenderer(<your-api-key>)
client: monix.eval.Task[com.gu.contentatom.renderer.utils.IoCapiRenderer] = Task.FlatMap$267799898
**scala>** client.flatMap(_.getArticle(("a025541b-c788-4e77-a175-c0574a855a04", AtomType.Guide)))
res0: monix.eval.Task[Option[com.gu.contentatom.renderer.ArticleAtomRenderer.HTML]] = Task.FlatMap$619160253
**scala>** res0.runSyncMaybe
res1: Either[monix.execution.CancelableFuture[Option[com.gu.contentatom.renderer.ArticleAtomRenderer.HTML]],Option[com.gu.contentatom.renderer.ArticleAtomRenderer.HTML]] = Left(Async(Future(<not completed>),monix.execution.cancelables.StackedCancelable$Impl@5e5f05e6))
Fetching atom/guide/a025541b-c788-4e77-a175-c0574a855a04

SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
**scala>** res1
res2: Either[monix.execution.CancelableFuture[Option[com.gu.contentatom.renderer.ArticleAtomRenderer.HTML]],Option[com.gu.contentatom.renderer.ArticleAtomRenderer.HTML]] =
Left(Async(Future(Success(Some(



<details data-snippet-id="a025541b-c788-4e77-a175-c0574a855a04" data-snippet-type="guide" class="atom atom--snippet atom--snippet--guide">
  <summary class="atom--snippet__header">
    <span class="atom--snippet__label">Quick guide</span>
    <h4 class="atom--snippet__headline">Scotland squad</h4>
    <button class="atom__button atom__button--large atom__button--rounded atom--snippet__handle" aria-hidden="true">
      <span class="is-on">

<svg class="icon icon--plus " width="18px" height="18px"
  viewBox="0 0 18 18"
  >

  <path d="M8.2 0h1.6l.4 7.8 7.8.4v1...
```

There are currently three methods:

- `getArticle: ((String, AtomType)) => Task[HTML]` produces the article rendering for an atom
- `getEmail: ((String, AtomType)) => Task[HTML]` produces the email rendering for an atom
- `getEmailAndSave: String => ((String, AtomType)) => Task[HTML]` as above but saves the content in the specified location

... and the answer is yes, I will make this way easier in the future.

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

* `src/main/resources/__flow__/types/atoms.fjs`

* `webpack/atomTypes.js` 

*Contact us in #journalism-team if you have problems with the repo / readme.* 

--

\* Yarn is used in these instructions, but you can use npm if you prefer 

