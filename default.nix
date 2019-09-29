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

  shellHook = nix-shell.pkgs.lib.concatStrings [
  # extra shellHook commands here
  ''
  ''
  nix-shell.shell.shellHook
  ];


  buildInputs = [
  ]
  ++ nix-shell.shell.buildInputs
  ;
 });
}
