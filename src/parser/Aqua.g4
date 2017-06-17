grammar Aqua;

options {
  language = Java;
}

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
INTEGER: '-'?[0-9]+;
WS        : [ \n\t\r]+ -> skip;

assay: 'ASSAY' IDENTIFIER 'START' decls stmts 'END' EOF;

/* DECLARATIONS */

decls
    : (decl ';')+
    ;

decl
    : fluid
    | input_
    | var
    | conflict
    ;

fluid
    : 'FLUID' name=IDENTIFIER dimension* ('WASH' IDENTIFIER)? ('PORT' INTEGER)?
    ;

input_
    : 'INPUT' IDENTIFIER INTEGER?
    ;

var
    : 'VAR' IDENTIFIER dimension*
    ;

dimension
    : '[' INTEGER ']'
    ;

conflict
    : 'CONFLICT' IDENTIFIER ('FOLLOWS' IDENTIFIER | ',' IDENTIFIER) ('WASH' IDENTIFIER)?
    ;

/* STATEMENTS */

stmts
    : (stmt ';' | control_stmt)+
    ;

control_stmt
    : repeat
    | for_loop
    ;

repeat
    : 'REPEAT' expr 'START' stmts 'ENDREPEAT'
    ;

for_loop
    : 'FOR' IDENTIFIER 'FROM' expr 'TO' expr 'START' stmts 'ENDFOR'
    ;

stmt
    : assign
    | mix
    | incubate
    | sense
    | /* empty statement */
    ;

assign
    : identifier '=' (mix | incubate) #assignFluid
    | identifier '=' expr                      #assignExpr
    ;

mix
    : 'MIX' identifier ('AND' identifier)+ ('IN RATIOS' expr (':' expr)+)? 'FOR' expr
    ;

incubate
    : 'INCUBATE' identifier 'AT' expr 'FOR' expr
    ;

sense
    : 'SENSE' sense_type identifier 'INTO' identifier
    ;

sense_type
    : 'FLUORESCENCE'
    | 'OPTICAL'
    ;

expr
    : expr op=('*'|'/') expr #MulDiv
    | expr op=('+'|'-') expr #AddSub
    | '(' expr ')'           #ParExpr
    | identifier             #VarExpr
    | INTEGER                #ConstExpr
    ;

identifier
    : IDENTIFIER index*
    ;

index
    : '[' expr ']'
    ;
