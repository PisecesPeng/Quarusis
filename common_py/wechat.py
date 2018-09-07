import itchat
import re

itchat.login()

user = itchat.get_friends()

i = str(user[:1])
# print(i)
info = i[8:-2]
# print(info)

uin = str(re.findall(r"'Uin': (.+?),", info))[2:-2]
print(uin)

