
import sys

arguments = sys.argv
#arguments = arguments.pop(0)

teams = arguments[1].split(",")

#teams = list(map(str, input("Enter a multiple value: ").split(",")))
if len(teams) % 2:
    teams.append('Free')
n = len(teams)
matchs = []
fixtures = []
return_matchs = []
for fixture in range(1, n):
    for i in range(int(n/2)):
        matchs.append((teams[i], teams[n - 1 - i]))
        #return_matchs.append((teams[n - 1 - i], teams[i]))
    teams.insert(1, teams.pop())
    fixtures.insert(int(len(fixtures)/2), matchs)
    fixtures.append(return_matchs)
    matchs = []
    return_matchs = []

for fixture in fixtures:
    for m in fixture:
        if m[0] == 'Free' or m[1] == 'Free':
            #do Nothing
            print('', end ='')
        else:
            print(m[0]+'-'+m[1])