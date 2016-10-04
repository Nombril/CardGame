package game.deck;

import game.Deck;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *    initialize, shuffle, deal, and check if empty.
 */
public enum CardDeck {
	/**
	 * The standard 52-card deck.
	 */
	STANDARD(CardRanks.STANDARD, CardSuits.STANDARD),
	
	//TODO Add more types of decks
	
	;

	private final String[] ranks;
	private final String[] suits;

	private CardDeck(String[] ranks, String[] suits) {
		this.ranks = ranks;
		this.suits = suits;
	}
	public Deck getDeck()
	{
		int[] values = new int[ranks.length];
		for(int i=0;i<values.length;++i)
			values[i]=0;
		return forPoints(values);
	}
	public Deck forPoints(int[] values)
	{
		if(values.length<ranks.length)
			throw new IllegalArgumentException("values.length is less than ranks.length.");
		
		return new Deck(ranks, suits, values);
	}
}
