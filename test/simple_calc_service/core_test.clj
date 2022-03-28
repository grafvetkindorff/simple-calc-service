(ns simple-calc-service.core-test
    (:require [clojure.test :refer :all]
      [simple-calc-service.core :refer :all]
      [simple-calc-service.parser :refer :all]))

(deftest simple-parser-test
  (testing "simple test"
    (is (= 100 (evaluate "100")))))

(deftest simple-parser-neg-test
  (testing "simple negation test"
    (is (= -100 (evaluate "-100")))))

(deftest simple-parser-parentheses-test
  (testing "simple parentheses test"
    (is (= -100 (evaluate "(-100)")))))

(deftest simple-parser-parentheses-test2
  (testing "simple parentheses test2"
    (is (= (* (- 101 100) 12) (evaluate "(101-100)*12")))))

(deftest simple-parser-parentheses-test3
  (testing "simple parentheses test2"
    (is (= (- 20 (* (- 101 100) 12)) (evaluate "20-(101-100)*12")))))

(deftest simple-parser-parentheses-test4
  (testing "simple parentheses test2"
    (is (= (- (* 20 (- 100 101)) (* (- 101 100) 12)) (evaluate "20*(100-101)-(101-100)*12")))))

(deftest simple-parser-invalid-expr-test
  (testing "simple parentheses test2"
    (is (= "invalid expression" (evaluate-wrapped "20*(100-101)-(101-100)*12 LOL KEK")))))

(deftest simple-parser-decimal-test
  (testing "simple test with decimals"
    (is (= 1328.3 (evaluate-wrapped "12.3+23.5*56")))))

