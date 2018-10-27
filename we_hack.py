# -*- coding: utf-8 -*-
"""
Created on Sat Sep  8 18:45:43 2018

@author: Naman Khurpia
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd



xls=pd.ExcelFile('Hack_A_Thon_DataSet_Rev1.xls')
dataset=pd.read_excel(xls,'Data_new')


X=dataset.iloc[:,1:10].values
y=dataset.iloc[:,9:].values



from sklearn.cross_validation import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)


from sklearn import metrics
from sklearn.ensemble import ExtraTreesClassifier


model = ExtraTreesClassifier()
model.fit(X_train,y_train)
print(model.feature_importances_)

from sklearn.ensemble import RandomForestRegressor
regressor = RandomForestRegressor(n_estimators = 28, random_state = 0)
regressor.fit(X, y)


y_pred = regressor.predict(X_test)


regressor.score(X_test, y_test)

X1=X[:,0]
X2=X[:,1]
X3=X[:,2]


plt.plot(X1, y, label = "line 1")


plt.legend() 
plt.show()   

