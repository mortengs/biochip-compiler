grammar Aqua;

options {
  language = Java;
}

prog: 'ASSAY' NAME 'START' (declarations NEWLINE)+ (statements NEWLINE)+ 'END';

declarations: fluid|var|input;

statements: repeat|for|mix|incubate|sense;

fluid: 'FLUID' NAME DIMENSION?;

var: 'VAR' NAME DIMENSION?;

input: 'INPUT' NAME NUM?;

repeat: 'REPEAT' NUM 'START' statements 'ENDREPEAT';

for: 'FOR' NAME 'FROM' NUM 'TO' NUM 'START' statements 'ENDFOR';

mix: 'MIX' NAME ('AND' NAME)+ ('IN RATIOS' NAME (: NAME)+)? 'FOR' NUM;

incubate: 'INCUBATE' NAME 'AT' NUM ('F'|'C') 'FOR' NUM;

sense: 'SENSE' SENSETYPE 'INTO' NAME;

SENSETYPE : 'OPTICAL'|'FLUORESCENCE';
NEWLINE   : [\r\n]+ ;
NUM       : ('0'..'9')+ ;
NAME      : ('a'..'z')+ ;
DIMENSION : ('['NUM']')+ ; 