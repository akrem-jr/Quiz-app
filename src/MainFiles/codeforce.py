#our_input = input()
# your_input.split("+")

# y.sort()
# print("+".join(y))
x= input()
x=int(x)
ans=[]
for i in range(x):
     x=input()
     ans.append(x) 
for i in range(x):
    if len(x[i]<=10):
        print(x[i])
    else:
        print(x[:1]+(len(x)-2)+x[len(x)-1:])