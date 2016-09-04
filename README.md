# Grails Gravatar Plugin
[![Build Status](https://travis-ci.org/rpalcolea/grails-gravatar.svg?branch=master)](https://travis-ci.org/rpalcolea/grails-gravatar)
[ ![Download](https://api.bintray.com/packages/rpalcolea/plugins/gravatar/images/download.svg) ](https://bintray.com/rpalcolea/plugins/gravatar/_latestVersion)
[![Slack Signup](http://slack-signup.grails.org/badge.svg)](http://slack-signup.grails.org)

This plugin provides a taglib for displaying gravatars.

Gravatars allow users to configure an avatar to go with their email address at a central location: gravatar.com. Gravatar-aware websites can then look up and display each user‘s preferred avatar, without having to handle avatar management. The user gets the benefit of not having to set up an avatar for each site that they post on.

Installation
------------
Add the following dependencies in `build.gradle`
```groovy
dependencies {
...
    compile 'rpalcolea.gravatar:gravatar:1.0.2'
...
}
```

Configuration
-----
You can modify the default rating and defaultImage in your application.yml as follows
```yml
gravatar:
    defaultRating: g #Optional (default is g)
    defaultGravatarUrl: http://docs.grails.org/latest/img/grails.png #optional
```

Usage
-----
###TagLib
Using the taglib is simple:

```gsp
<gravatar:image email="roberto@perezalcolea.info"/>
```

This will output

```html
<img id="" name="" alt="Gravatar" class="gravatar" height="80" width="80" src="https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g" title="gravatar"/>
```

You can provide the following arguments to the TagLib:

```groovy
 /**
     * @attr email    REQUIRED	the startDate for styling
     * @attr size          the disired dimensions in pixels for the gravatar image (from 1 up to 512)
     * @attr alt          alt-attribute for the resulting img-element
     * @attr cssClass        class-attribute for the resulting img-element
     * @attr title          title-attribute for the resulting img-element
     * @attr id            id-attribute for the resulting img-element
     * @attr name          name-attribute for the resulting img-element
     * @attr defaultGravatarUrl    the default image to display if no gravatar is found; may be a URL or one of the following (defaults to the official Gravatar logo):
     * 								<li>404: do not load any image if none is associated with the email hash, instead return an HTTP 404 (File Not Found) response
     * 								<li>mm: (mystery-man) a simple, cartoon-style silhouetted outline of a person (does not vary by email hash)
     * 								<li>identicon: a geometric pattern based on an email hash
     * 								<li>monsterid: a generated 'monster' with different colors, faces, etc
     * 								<li>wavatar: generated faces with differing features and backgrounds
     * 								<li>retro: awesome generated, 8-bit arcade-style pixelated faces
     * @attr gravatarRating      desired image rating censor-level; may be one of the following:
     * 								<li>g (default): suitable for display on all websites with any audience type.
     * 								<li>pg: may contain rude gestures, provocatively dressed individuals, the lesser swear words, or mild violence.
     * 								<li>r: may contain such things as harsh profanity, intense violence, nudity, or hard drug use.
     * 								<li>x: may contain hardcore sexual imagery or extremely disturbing violence.
     */
```
###GravatarUrlGenerator
If you want to generate links without the use of the taglib, you can accomplish it by using `GravatarUrlGenerator` as follows:

```groovy
GravatarUrlGenerator.generateUrl('roberto@perezalcolea.info')
```

This will output

```
https://www.gravatar.com/avatar/ae03f5244dfbbd216864590baacfd130?s=80&r=g
```

You can provide the following arguments:

```groovy
  GravatarRating rating
  Integer size
  String defaultImage
```

Acknowledgements
-------
This plugin contains original code and the ideas from the [Avatar Plugin](https://github.com/domix/avatarplugin) for Grails `1.x` by Domingo Suarez Torres (@domix) 

License
-------
Apache 2

Sponsors
-------
[![Alt text](https://www.yourkit.com/images/yklogo.png "YourKit")](https://www.yourkit.com/.net/profiler/index.jsp)

YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of <a href="https://www.yourkit.com/java/profiler/index.jsp">YourKit Java Profiler</a>
and <a href="https://www.yourkit.com/.net/profiler/index.jsp">YourKit .NET Profiler</a>,
innovative and intelligent tools for profiling Java and .NET applications.
