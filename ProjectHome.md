# Overview #
Since June 20. the GW2 API servers use a SSL certificate signed by GeoTrust, and the Java default KeyStore comes with the GeoTrust root certificate installed.
Due to that the SSLHelpers are no longer needed, but can still be found on revision:
http://code.google.com/p/j-gw2-api/source/browse/?r=7e2311b7e258e4e315b2aa3d8e75ca35ee0fdbff
or earlier.

The project still servers as a helper library.

More informations about the GW2 API can be found in the [official GW2 forums](https://forum-en.guildwars2.com/forum/community/api) or on the official [GW2 Wiki](http://wiki.guildwars2.com/wiki/API).

Suggestion can be made and future milestones can be found in the Issue tracker.

# Informations #

New high level APIs for build, guilds and colors are now available.
Guild emblem data is missing flags currently (until I have a list of all available flags).