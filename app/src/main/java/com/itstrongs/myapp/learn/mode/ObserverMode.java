package com.itstrongs.myapp.learn.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计模式 —— Observer模式
 * <p>
 * Created by itstrongs on 2018/1/3.
 */
public class ObserverMode {

    public static void main(String[] args) {
        Observable<Weather> observable = new Observable<Weather>();

        Observer<Weather> observer1 = new Observer<Weather>() {
            @Override
            public void onUpdate(Observable<Weather> observable, Weather data) {
                System.out.println("观察者1：" + data.toString());
            }
        };
        Observer<Weather> observer2 = new Observer<Weather>() {
            @Override
            public void onUpdate(Observable<Weather> observable, Weather data) {
                System.out.println("观察者2：" + data.toString());
            }
        };

        observable.register(observer1);
        observable.register(observer2);


        Weather weather = new Weather("晴转多云");
        observable.notifyObservers(weather);

        Weather weather1 = new Weather("多云转阴");
        observable.notifyObservers(weather1);

        observable.unregister(observer1);

        Weather weather2 = new Weather("台风");
        observable.notifyObservers(weather2);
    }

    public static class Weather {

        private String description;

        public Weather(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "description='" + description + '\'' +
                    '}';
        }
    }

    public static class Observable<T> {

        List<Observer<T>> mObservers = new ArrayList<Observer<T>>();

        public void register(Observer<T> observer) {
            if (observer == null) {
                throw new NullPointerException("observer == null");
            }
            synchronized (this) {
                if (!mObservers.contains(observer))
                    mObservers.add(observer);
            }
        }

        public synchronized void unregister(Observer<T> observer) {
            mObservers.remove(observer);
        }

        public void notifyObservers(T data) {
            for (Observer<T> observer : mObservers) {
                observer.onUpdate(this, data);
            }
        }
    }

    public interface Observer<T> {

        void onUpdate(Observable<T> observable, T data);
    }
}
