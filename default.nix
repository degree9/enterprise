let
 nix-shell-release-tag = "7ec672ca3aaeb6c4b8fbc416a94c778887bddfe7";
 nix-shell-release-sha256 = "147xvybp8r85xiyzmaww4m21i6a17fdkz6ygvhqigjsbpwq9qg4v";

 nix-shell = import (fetchTarball {
  url = "https://github.com/thedavidmeister/nix-shadow-cljs/tarball/${nix-shell-release-tag}";
  sha256 = "${nix-shell-release-sha256}";
 });
in
with nix-shell.pkgs;
{
 core-shell = stdenv.mkDerivation (nix-shell.shell // {
  name = "core-shell";

  SUPPRESS_NO_CONFIG_WARNING = "true";

  shellHook = nix-shell.pkgs.lib.concatStrings [
  # extra shellHook commands here
  ''
  ''
  nix-shell.shell.shellHook
  ];


  buildInputs = [
   (nix-shell.pkgs.writeShellScriptBin "flush"
   ''
   rm -rf ./node_modules
   rm -rf ./test
   rm -rf ./.shadow-cljs
   rm -rf ./repl-public
   rm -rf ./repl-node
   rm ./package-lock.json
   '')

   (nix-shell.pkgs.writeShellScriptBin "node-test"
   ''
   set -euxo pipefail
   shadow-cljs compile node-test
   '')

   (nix-shell.pkgs.writeShellScriptBin "browser-test"
   ''
   set -euxo pipefail
   shadow-cljs compile browser-test
   karma start --single-run --browsers FirefoxHeadless
   '')
  ]
  ++ nix-shell.shell.buildInputs
  ;
 });
}
