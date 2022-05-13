function plotWholeData(x,y,z,t)
%PLOTWHOLEDATA Summary of this function goes here
%   Detailed explanation goes here


figure(1)
plot(x, y)

hold on
plot(x, z)
plot(x, t)
hold off
grid
legend('Susceptible', 'Infected', 'Removed', 'Location', 'SW')
end

