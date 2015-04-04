(ns kit.async
  (:require
    [cljs.core.async :as async :refer (close! put! chan)]))

(defn lift
  "Takes a function and some arguments and returns a channel.
   Applies the function to the arguments. Additionally a
   a callback function of one argument is supplied to the
   function. Once the callback gets called its argument is
   put on the returned channel"
  [f & args]
  (let [c  (chan)
        cb (fn [x]
             (if (or (nil? x)
                     (undefined? x))
               (close! c)
               (put! c x)))]
    (apply f (concat args [cb]))
    c))

(defn lift-node-cb
  "Takes a function and some arguments and returns a channel.
   Applies the function to the arguments. Additionally a
   standard [err result] callback is added as the last parameter.
   If an error occurs, it is put on the channel, otherwise
   the result value is put on the returned channel"
  [f & args]
  (let [c  (chan)
        cb (fn [err x]
             (if err
               (put! c err)
               (if (or (nil? x)
                       (undefined? x))
                 (close! c)
                 (put! c x))))]
    (apply f (concat args [cb]))
    c))

(defprotocol EventSource
  "The EventSource protocol can be extended by anything
   that wants to provide a simple way to subscribe to named
   events. Extenders of this protocol can automatically be
   used with the otehr wrappers in this namespace"
  (on [_ evt f]))

(def <on (partial lift on))
