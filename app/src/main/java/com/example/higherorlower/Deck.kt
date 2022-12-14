package com.example.higherorlower

class Deck() {
    private val deck = mutableListOf<Card>()
    private val usedDeck = mutableListOf<Card>()

    init {
        createDeck()
    }

    fun randomNewCard(previousCard: Card): Card? {
        var card: Card? = null
        var shuffleAgain = true
        while(shuffleAgain) {
            deck.shuffle()
            card = deck[10]
            if (deck.size == usedDeck.size) {
                usedDeck.clear()
            }
            if(previousCard.value != card.value) {
                if(card !in usedDeck) {
                    usedDeck.add(card)
                    shuffleAgain = false
                }
            }
        }
        return card
    }

    fun randomPreviousCard(): Card {
        var card: Card?
        deck.shuffle()
        card = deck[8]
        usedDeck.add(card)
        return card
    }

//    fun checkNotUsedDeck(card: Card): Boolean {
//        if (deck.size == usedDeck.size) {
//            usedDeck.clear()
//        }
//        if (card !in usedDeck) {
//            usedDeck.add(card)
//            return true
//        }
//        return false
//    }

    fun createDeck() {
        val card1 = Card(R.drawable.heartace, 1)
        val card2 = Card(R.drawable.heart2, 2)
        val card3 = Card(R.drawable.heart3, 3)
        val card4 = Card(R.drawable.heart4, 4)
        val card5 = Card(R.drawable.heart5, 5)
        val card6 = Card(R.drawable.heart6, 6)
        val card7 = Card(R.drawable.heart7, 7)
        val card8 = Card(R.drawable.heart8, 8)
        val card9 = Card(R.drawable.heart9, 9)
        val card10 = Card(R.drawable.heart10, 10)
        val card11 = Card(R.drawable.heartj, 11)
        val card12 = Card(R.drawable.heartq, 12)
        val card13 = Card(R.drawable.heartk, 13)
        val card14 = Card(R.drawable.clubace, 1)
        val card15 = Card(R.drawable.club2, 2)
        val card16 = Card(R.drawable.club3, 3)
        val card17 = Card(R.drawable.club4, 4)
        val card18 = Card(R.drawable.club5, 5)
        val card19 = Card(R.drawable.club6, 6)
        val card20 = Card(R.drawable.club7, 7)
        val card21 = Card(R.drawable.club8, 8)
        val card22 = Card(R.drawable.club9, 9)
        val card23 = Card(R.drawable.club10, 10)
        val card24 = Card(R.drawable.clubj, 11)
        val card25 = Card(R.drawable.clubq, 12)
        val card26 = Card(R.drawable.clubk, 13)
        val card27 = Card(R.drawable.spadesace, 1)
        val card28 = Card(R.drawable.spades2, 2)
        val card29 = Card(R.drawable.spades3, 3)
        val card30 = Card(R.drawable.spades4, 4)
        val card31 = Card(R.drawable.spades5, 5)
        val card32 = Card(R.drawable.spades6, 6)
        val card33 = Card(R.drawable.spades7, 7)
        val card34 = Card(R.drawable.spades8, 8)
        val card35 = Card(R.drawable.spades9, 9)
        val card36 = Card(R.drawable.spades10, 10)
        val card37 = Card(R.drawable.spadesj, 11)
        val card38 = Card(R.drawable.spadesq, 12)
        val card39 = Card(R.drawable.spadesk, 13)
        val card40 = Card(R.drawable.diamondsace, 1)
        val card41 = Card(R.drawable.diamonds2, 2)
        val card42 = Card(R.drawable.diamonds3, 3)
        val card43 = Card(R.drawable.diamonds4, 4)
        val card44 = Card(R.drawable.diamonds5, 5)
        val card45 = Card(R.drawable.diamonds6, 6)
        val card46 = Card(R.drawable.diamonds7, 7)
        val card47 = Card(R.drawable.diamonds8, 8)
        val card48 = Card(R.drawable.diamonds9, 9)
        val card49 = Card(R.drawable.diamonds10, 10)
        val card50 = Card(R.drawable.diamondsj, 11)
        val card51 = Card(R.drawable.diamondsq, 12)
        val card52 = Card(R.drawable.diamondsk, 13)

        deck.add(card1)
        deck.add(card2)
        deck.add(card3)
        deck.add(card4)
        deck.add(card5)
        deck.add(card6)
        deck.add(card7)
        deck.add(card8)
        deck.add(card9)
        deck.add(card10)
        deck.add(card11)
        deck.add(card12)
        deck.add(card13)
        deck.add(card14)
        deck.add(card15)
        deck.add(card16)
        deck.add(card17)
        deck.add(card18)
        deck.add(card19)
        deck.add(card20)
        deck.add(card21)
        deck.add(card22)
        deck.add(card23)
        deck.add(card24)
        deck.add(card25)
        deck.add(card26)
        deck.add(card27)
        deck.add(card28)
        deck.add(card29)
        deck.add(card30)
        deck.add(card31)
        deck.add(card32)
        deck.add(card33)
        deck.add(card34)
        deck.add(card35)
        deck.add(card36)
        deck.add(card37)
        deck.add(card38)
        deck.add(card39)
        deck.add(card40)
        deck.add(card41)
        deck.add(card42)
        deck.add(card43)
        deck.add(card44)
        deck.add(card45)
        deck.add(card46)
        deck.add(card47)
        deck.add(card48)
        deck.add(card49)
        deck.add(card50)
        deck.add(card51)
        deck.add(card52)
    }
}
