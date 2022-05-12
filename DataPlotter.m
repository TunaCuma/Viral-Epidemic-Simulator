data = load('faik.txt'); % read comma separated data
X = data(:, 1); y = data(:, 2); z= data(:,3); t = data(:,4);



plotWholeData(X,y,z,t);