;;;; Imports data from a return separated sample_data file & displays it using a histogram
(use '(incanter core charts datasets stats io))

;; Initial dataset to compare against
(def data
  (read-dataset "/Users/jburris/Development/Zeevex/incanter/purchase-time-to-completions.csv"
                :delim \return
                :header true))

;; Displays a dataset with a 'Seconds' column using histogram
(defn show-histogram [dataset]
  (view (histogram
       :Seconds
       :data dataset
       :y-label "Count")))

;; Specifies the range to work with for the data
(defn range-data [dataset gt lt]
  (with-data dataset (->
                      ($where
                       {:Seconds {:$lt lt :$gt gt }}))))

;; Ranges we're looking to gather histograms from
(def ranges [[-100 0] [0 20] [20 100] [100 1000] [1000 500000] [500000 3000000] ] )

;; map over all of the range options, displaying a histogram for each
(map
 (fn [[gt lt]] (show-histogram (range-data data gt lt)))
 ranges)
