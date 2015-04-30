# Macro

* programmable programming languages
* extend the Clojure language
* Macros are powerful because they give you a way to define entirely new levels of abstraction within the language itself.

## What is a Macro

* Macros allow you to control the Clojure compiler
* distinction between runtime and compile time

* homoiconicity enables macros
* Clojure source code is read by the Clojure reader, which produces Clojure data structures from textual Clojure code—the very same Clojure data structures you have access to in your own code
* `"(foo [bar] :baz 123)"`, the reader yields a list

* between the read and evaluation steps, macros occupy a privileged status compared to functions.
* __macros are called by the compiler__ with their unevaluated data structures as arguments and must return a Clojure data structure that can itself be evaluated.


```
(foo a b)
```
invocation of the foo function

```
(bar a b)
```
if bar is a macro, then bar is called by the Clojure compiler with two arguments, the symbols a and b—not the values they name

A macro must return to the compiler a Clojure data structure 
that will be used in its place. This is recursive, 
as a macro can return a Clojure data structure that includes 
other macro calls as well; this continues for each expression 
until it is no longer a macro call.

1. Clojure source code
2. Clojure data structures
3. Compilation
4. Macro call ?
  1. Yes. Macroexpansion. go to 2
  2. No. Function or special form call ? 
    1. Yes.  Bytecode generation then Evaluation
	2. No. Evaluaton
	
The compilation process ensures that any macro calls are replaced wholesale 
with their expansions long before a program’s runtime; thus, macros are 
only ever evaluated at compile time.

## What Can Macros Do that Functions Cannot ?

