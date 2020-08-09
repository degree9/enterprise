(ns degree9.twilio.video)



(defn connect [client token & args]
  (.connect client token args))

(defn create-local-audio-track [client & args]
  (.createLocalAudioTrack client args))

(defn create-local-tracks [client & args]
  (.createLocalTracks client args))

(defn create-local-video-track [client & args]
  (.createLocalVideoTrack client args))
