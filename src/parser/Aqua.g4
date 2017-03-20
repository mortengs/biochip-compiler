grammar Aqua;

options {
  language = Java;
}

assay: 'ASSAY' IDENTIFIER 'START' decls stmts 'END' EOF;

decls: (decl ';')+;

decl: fluid | input | var | conflict;

// Declarations

fluid: 'FLUID' IDENTIFIER dimension* ('WASH' IDENTIFIER)? ('PORT' INTEGER)?;

input: 'INPUT' IDENTIFIER INTEGER?;

var: 'VAR' IDENTIFIER dimension*;

dimension: '[' INTEGER ']';

conflict: 'CONFLICT' IDENTIFIER ('FOLLOWS' IDENTIFIER | ',' IDENTIFIER) ('WASH' IDENTIFIER)?;

// Statements

stmts: (stmt ';' | control_stmt)+;

control_stmt: repeat | for_loop;

repeat: 'REPEAT' expr 'START' stmts 'ENDREPEAT';

for_loop: 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR';

stmt: assign
  | mix
  | incubate
  | sense
  | /* empty statement */;

assign: IDENTIFIER '=' (mix | incubate) | IDENTIFIER '=' expr;

mix: 'MIX' IDENTIFIER ('AND' IDENTIFIER)+ ('IN RATIOS' expr (':' expr)+)? 'FOR' expr;

incubate: 'INCUBATE' IDENTIFIER 'AT' expr 'FOR' expr;

sense: 'SENSE' sense_type IDENTIFIER 'INTO' IDENTIFIER;

sense_type: 'FLUORESCENCE' | 'OPTICAL';

expr: expr ('*' | '/') expr
  | expr ('x' | '-') expr
  | '(' expr ')'
  | identifier
  | INTEGER;

identifier: IDENTIFIER index*;

index: '[' expr ']';

IDENTIFIER: ('a'..'z'|'A'..'Z'|'_')+ ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
INTEGER   : '-'?('0'..'9')+;
WS        : [ \n\t\r]+ -> skip;