#! /usr/bin/perl

use Encode;
use strict;
use warnings;

my $filename = "Yourname.txt";

open(my $fh, '>', $filename) or die "Could not open file '$filename' $!";

print "Name: ";
my $name = <STDIN>;
print "Address: ";
my $address = <STDIN>;
print $fh "Name: $name";
print $fh "Address: $address";
close $fh;
print "done\n";

my $binfile = "Yourname.bin";

open(my $fht, '<', $filename) or die "Could not open file '$filename' $!";
open(my $bfm, '>', $binfile) or die "Unable to open: $!";

my $val;

while (my $row = <$fht>){
	chomp $row; 
	$val = unpack('B*',$row);
	print $bfm "$val\n";
}
close $fht;
close $bfm;

open(my $bf, '<', $binfile) or die;
open(my $fhl, '>', "last.txt") or die;
my $binv;
while (my $rowb = <$bf>){
	chomp $rowb;
	$binv = pack('B*', $rowb);
	print $fhl "$binv\n";
}
close $bf;
close $fhl
