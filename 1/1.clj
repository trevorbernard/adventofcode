(defn abs [n] (max n (-' n)))

(defn distance [p q]
  (let [[p1 p2] p
        [q1 q2] q]
    (+ (abs (- p1 q1))
       (abs (- p2 q2)))))

(defmulti traverse (fn [o _ _] o))

(defmethod traverse :north [_ instruction [x y]]
  (let [dir (first instruction)
        amount (read-string (apply str (rest instruction)))]
    (if (= \L dir)
      [:west [(- x amount) y]]
      [:east [(+ x amount) y]])))
(defmethod traverse :south [_ instruction [x y]]
  (let [dir (first instruction)
        amount (read-string (apply str (rest instruction)))]
    (if (= \L dir)
      [:east [(+ x amount) y]]
      [:west [(- x amount) y]])))
(defmethod traverse :east [_ instruction [x y]]
  (let [dir (first instruction)
        amount (read-string (apply str (rest instruction)))]
    (if (= \L dir)
      [:north [x (+ y amount)]]
      [:south [x (- y amount)]])))
(defmethod traverse :west [_ instruction [x y]]
  (let [dir (first instruction)
        amount (read-string (apply str (rest instruction)))]
    (if (= \L dir)
      [:south [x (- y amount)]]
      [:north [x (+ y amount)]])))

(def input1 ["R2", "L3"])
(def input2 ["R2", "R2", "R2"])
(def input3 ["R5", "L5", "R5", "R3"])
(def input ["L3","R2","L5","R1","L1","L2","L2","R1","R5","R1","L1","L2","R2","R4","L4","L3","L3","R5","L1","R3","L5","L2","R4","L5","R4","R2","L2","L1","R1","L3","L3","R2","R1","L4","L1","L1","R4","R5","R1","L2","L1","R188","R4","L3","R54","L4","R4","R74","R2","L4","R185","R1","R3","R5","L2","L3","R1","L1","L3","R3","R2","L3","L4","R1","L3","L5","L2","R2","L1","R2","R1","L4","R5","R4","L5","L5","L4","R5","R4","L5","L3","R4","R1","L5","L4","L3","R5","L5","L2","L4","R4","R4","R2","L1","L3","L2","R5","R4","L5","R1","R2","R5","L2","R4","R5","L2","L3","R3","L4","R3","L2","R1","R4","L5","R1","L5","L3","R4","L2","L2","L5","L5","R5","R2","L5","R1","L3","L2","L2","R3","L3","L4","R2","R3","L1","R2","L5","L3","R4","L4","R4","R3","L3","R1","L3","R5","L5","R1","R5","R3","L1"])

(defn solve [inputs]
  (let [[o dest] (reduce (fn [[orientation acc] input]
                           (traverse orientation input acc)) [:north [0, 0] ] inputs)]
    (distance [0, 0] dest)))


(defn my-range [a b]
  (if (> b a)
    (range a (inc b))
    (range b (inc a))))

(defmulti fills (fn [o _ _] o))
(defmethod fills :north [_ [x1, y1] [x2, y2]]
  (reduce (fn [acc a] (conj acc [a y1])) #{} (my-range x1 x2)))
(defmethod fills :south [_ [x1, y1] [x2, y2]]
  (reduce (fn [acc a] (conj acc [a y1])) #{} (my-range x1 x2)))
(defmethod fills :east [_ [x1, y1] [x2, y2]]
  (reduce (fn [acc a] (conj acc [x1 a])) #{} (my-range y1 y2)))
(defmethod fills :west [_ [x1, y1] [x2, y2]]
  (reduce (fn [acc a] (conj acc [x1 a])) #{} (my-range y1 y2)))

(defn solve2 [inputs]
  (loop [visited #{}
         curr [:north [0, 0]]
         [input & inputs] inputs]
    (if input
      (let [next (traverse (first curr) input (second curr))
            points (disj (fills (first curr) (second curr) (second next))
                         (second curr))]
        (if (seq (clojure.set/intersection visited points))
          (distance [0, 0] (first (clojure.set/intersection visited points)))
          (recur
           (clojure.set/union visited points)
           next
           inputs)))
      -1)))
#_ (fills :north [0,0], [8, 0])
#_ (solve input)
#_ (solve2 ["R8", "R4"])
#_ (solve2 ["R8", "R4", "R4", "R8"])
#_ (solve2 input)
