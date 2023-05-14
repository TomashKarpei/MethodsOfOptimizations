import sympy as sp
lambd = sp.Symbol('lambda')

def f(x):
  #return x[0] * x[1] * x[2]
  return x[0]*x[0] + x[1]*x[1]

def h(x):
  #return 6 - x[0] - x[1] - x[2]
  return 2*x[0] + x[1] - 2
def L(x):
  return f(x) + lambd * h(x)

X = []
for i in range(2):
  X.append(sp.Symbol(f'x{i}'))

MetLagr = [sp.Eq(L(X).diff(lambd), 0)]
for i in range(len(X)):
  MetLagr.append(sp.Eq(L(X).diff(X[i]), 0))
  print(sp.solve(MetLagr))