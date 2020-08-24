from sympy.solvers import solve
from sympy import Symbol
x=Symbol('x')
print(solve(x**50 + 2 * x**4 - 3 * x**2 + x + 1, x))

#it takes some time to compute all the solutions
