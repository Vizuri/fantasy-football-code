package com.vizuri.fantasy.football;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

//http://www.giocc.com/writing-a-lexer-in-java-1-7-using-regex-named-capturing-groups.html
public class PlayByPlayLexer {
	private final static transient Logger log = Logger.getLogger(PlayByPlayLexer.class);
	
	public static enum TokenType {
		PLAYER("[A-Z]\\.[A-Z][a-z]+"),
		IGNORE("(?i)(\\W|^)touchback|pass incomplete|no play(\\W|$)"),
		PLAYREVERSED("(?i)(\\W|^)play was REVERSED(\\W|$)"),
		TACKLERS("\\([A-Z]\\.[A-Z][a-z]+.*?\\)"),
		BACKWARDPASS("(?i)Backward pass to"),
		PASSPLAY("pass.*? to"),
		PASSINTERCEPTION("pass.*? intended for"),
		YARDAGE("-?[0-9]+ yards"),
		TOUCHDOWNNULLIFIED("(\\W|^)TOUCHDOWN NULLIFIED(\\W|$)"),
		TOUCHDOWN("(\\W|^)TOUCHDOWN(\\W|$)"),
		PUNT("(?i)(\\W|^)punts(\\W|$)"),
		KICKOFF("(?i)(\\W|^)kicks(\\W|$)"),
		FUMBLES("(?i)(\\W|^)fumbles(\\W|$)"),
		SACK("(?i)(\\W|^)sacked(\\W|$)"),
		RECOVEREDBY("(?i)recovered by [A-Z]{2,3}"),
		INTERCEPTION("(?i)(\\W|^)intercepted(\\W|$)"),
		FIELDGOAL("[0-9]+ yard field goal is(?i)(\\W|^)good(\\W|$)"),
		EXTRAPOINT("extra point is(?i)(\\W|^)good(\\W|$)"),
		PUNTBLOCK("(?i)punt is blocked"),
		SAFETY("(?i)safety"),
		TWOPOINTCONVERSION("(?i)attempt succeeds"),
		FINALSCORE("††††††††††††††††††††††"),
		
		WHITESPACE("[ \t\f\r\n]+",true),
		//PARENTHESIZED("\\(.+?\\)",true),
		PARENTHESIZED("\\(([^\\)]+)\\)",true),
		UNKNOWNWORD("[^\\s]+", true);
		
		public final String pattern;
		public final boolean swallow;
		
		private TokenType(String pattern) {
			this(pattern, false);
		}
		
		private TokenType(String pattern, boolean swallow) {
			this.pattern = pattern;
			this.swallow = swallow;
		}
	}
	
	public static class Token {
		public String data;
		
		public Token(String data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return data;
		}
	}
	
	public static Map<TokenType, List<Token>> lexit(String input) {
		Map<TokenType,List<Token>>tokenMap = new HashMap<TokenType,List<Token>>();
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (TokenType tokenType : TokenType.values()) {
			tokenMap.put(tokenType, new ArrayList<Token>());
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		}
            
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        // Match tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            for (TokenType type : TokenType.values()) {
            	if (matcher.group(type.name()) != null) {
            		if (!type.swallow) {
            			tokenMap.get(type).add(new Token(matcher.group(type.name())));
            		}
            		continue;
            	}
            }
        }
        
        // Clean up
        for (TokenType type : TokenType.values()) {
        	if (tokenMap.get(type).size() > 0) {
	        	if (log.isDebugEnabled()) { log.debug(type + " tokenCount: " + tokenMap.get(type).size()); }
	        	for (Token token : tokenMap.get(type)) {
	        		switch (type) {
	        		case YARDAGE:
	        			token.data = token.data.replaceAll(" yards", "");
	        			break;
	        		case TACKLERS:
	        			token.data = token.data.replaceAll("\\(", "").replaceAll("\\)", "");
	        			break;
	        		case FIELDGOAL:
	        			token.data = token.data.replaceAll("[^\\d.]", "");
	        			break;
	        		case RECOVEREDBY:
	        			token.data = token.data.replaceAll("recovered by ", "");
	        			break;
	        		default:
	        			break;
	        		}
	        		if (log.isDebugEnabled()) { log.debug(" >>> " + token); }
	        	}
        	} else {
        		tokenMap.remove(type);
        	}
        }
		
		return tokenMap;
	}
}
