# SE102 Programming Assignment 3 - Infix to Postfix Converter

## Peerawit Yawirach 682115032

## Linked List-Based Stack

---

## Files
- `Node.java`         — Node class for the linked list
- `Stack.java`        — Stack class implemented with a linked list
- `InfixToPostfix.java` — Main program: validates and converts infix expressions

---

## How to Compile

Open a terminal in the folder containing the `.java` files and run:

```
javac Node.java Stack.java InfixToPostfix.java
```

---

## How to Run

Create a plain text file named `input1.txt` with one infix expression per line.  
Then run:

```
java InfixToPostfix input1.txt
```

---

## Example input1.txt

```
a-b/(c+d-e)*(f^g*h+i)
1+2+3^4**
```

---

## Example Output

```
Expression 1:
Infix exp:a-b/(c+d-e)*(f^g*h+i)
Valid
Postfix exp: abcd+e-/fg^h*i+*-

Expression 2:
Infix exp:1+2+3^4**
Not-Valid
```

---

## Notes
- Supported operators: `+`, `-`, `*`, `/`, `^`
- `^` is treated as right-associative (higher precedence)
- Operands can be letters or digits
- Expressions with invalid characters, mismatched parentheses, or consecutive operators/operands are marked **Not-Valid**
