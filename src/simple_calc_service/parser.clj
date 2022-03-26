(ns simple-calc-service.parser
  (:require [instaparse.core :as insta]
            [clojure.string :as s]))

(def arithmetic-exprs-parser
  (insta/parser
    "expr                  = additive-expr
     <additive-expr>       = multiplicative-expr | add | sub
     <multiplicative-expr> = term | mul | div
     add                   = additive-expr <'+'> multiplicative-expr
     sub                   = additive-expr <'-'> multiplicative-expr
     mul                   = multiplicative-expr <'*'> term
     div                   = multiplicative-expr <'/'> term
     <term>                = number | <'('> additive-expr <')'>
     number                = #'-?[0-9]+'"))

(defn evaluate [expr-str]
  (->> (s/replace expr-str #"\s" "")
       (arithmetic-exprs-parser)
       (insta/transform
         {:add +,
          :sub -,
          :mul *,
          :div /,
          :number clojure.edn/read-string
          :expr identity})))

(defn evaluate-wrapped [expr-str]
  (let [answer (evaluate expr-str)]
    (if (int? answer)
      answer
      "invalid expression")))