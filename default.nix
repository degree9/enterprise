let
 nix-shell-release-tag = "2e4602b63f058842230f0ab7693cca0af98facca";
 nix-shell-release-sha256 = "0r37nfml2qqqkgdvbc317z2cdkqdalywq7771w55lj5j7mznjjhv";

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
