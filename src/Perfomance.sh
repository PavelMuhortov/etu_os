#!/bin/bash

echo -n "Теоретическое быстродействие (количество операций в секунду):"
read theorPerf
echo -n "Частота сбоев (через каждое количество операций):"
read failFreq
echo -n "Время обработки сбоя (количество операций на сбой):"
read failTime

let "realPerf=$theorPerf-(($theorPerf/$failFreq)*$failTime)"
echo "Реальное быстродействие: $realPerf=$theorPerf-(($theorPerf/$failFreq)*$failTime)"

read -p "Press ENTER to continue"
