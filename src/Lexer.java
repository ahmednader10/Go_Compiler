import java.lang.System;
import java.io.*;
class Lexer {
	Yylex tokenizer;
	public  Lexer(String fileName) 
	{
	  try
	  {
	  tokenizer=new Yylex(new BufferedReader(new FileReader(fileName)));
	  }
	  catch(Exception e)
	  {
	  }	 
	}
	public Token nextToken()
	{
		Token next=null;
		try
		{
		 next=  tokenizer.getToken();
		}
		catch(Exception e)
		{
		}
		return next;
	}
	}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	//initialize  variables to be used by class
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

//Add code to be executed on initialization of the lexer
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,130,
"42:8,3,17,0,42,11,19,42:18,1,38,42:3,39,21,42,24,25,35,22,31,23,32,41,2:10," +
"33,30,36,34,37,42:2,1:26,28,40,29,42,1,42,6,1,13,8,5,1:5,7,1:2,12,16,15,1,4" +
",14,10,9,1:3,18,1,26,20,27,42:2,43:2")[0];

	private int yy_rmap[] = unpackFromString(1,60,
"0,1,2,3,4,5,6,2:9,7,2:20,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,2" +
"5,26,27,28,29,30")[0];

	private int yy_nxt[][] = unpackFromString(31,44,
"-1,1,2,38,1:7,40,1:5,42,1,37,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,44,46" +
",19,20,48,21,2,22,-1,1:2,-1,1:7,-1,1:5,-1,1,-1:89,23,-1:44,24,-1:44,25,-1:4" +
"4,26,-1:54,27,-1:14,45,-1:42,59,-1:51,50,-1:40,39,-1:6,41,-1:31,31,-1:57,43" +
",-1:40,51,-1:51,28,-1:12,29,-1:17,52,-1:70,30,-1:14,53,-1:48,47,-1:36,54,-1" +
":50,32,-1:35,33,-1:47,55,-1:50,56,-1:34,34,-1:40,57,-1:49,58,-1:45,35,-1:45" +
",36,-1:34,49,-1:38");

	public Token getToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

//Add code to be executed when the end of the file is reached
return (new Token(Token.EOF,yytext()));
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{return (new Token(Token.IDENTIFIER,yytext()));}
					case -2:
						break;
					case 2:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -3:
						break;
					case 3:
						{return (new Token(Token.BAR,yytext()));}
					case -4:
						break;
					case 4:
						{return (new Token(Token.AMBERSAND,yytext()));}
					case -5:
						break;
					case 5:
						{return (new Token(Token.PLUS,yytext()));}
					case -6:
						break;
					case 6:
						{return (new Token(Token.MINUS,yytext()));}
					case -7:
						break;
					case 7:
						{return (new Token(Token.OPEN_PARAN,yytext()));}
					case -8:
						break;
					case 8:
						{return (new Token(Token.CLOSE_PARAN,yytext()));}
					case -9:
						break;
					case 9:
						{return (new Token(Token.OPEN_CURLY,yytext()));}
					case -10:
						break;
					case 10:
						{return (new Token(Token.CLOSE_CURLY,yytext()));}
					case -11:
						break;
					case 11:
						{return (new Token(Token.OPEN_SQUARE,yytext()));}
					case -12:
						break;
					case 12:
						{return (new Token(Token.CLOSE_SQUARE,yytext()));}
					case -13:
						break;
					case 13:
						{return (new Token(Token.SEMI_COLON,yytext()));}
					case -14:
						break;
					case 14:
						{return (new Token(Token.COMMA,yytext()));}
					case -15:
						break;
					case 15:
						{return (new Token(Token.DOT,yytext()));}
					case -16:
						break;
					case 16:
						{return (new Token(Token.COLON,yytext()));}
					case -17:
						break;
					case 17:
						{return (new Token(Token.EQUAL,yytext()));}
					case -18:
						break;
					case 18:
						{return (new Token(Token.ASTRISK,yytext()));}
					case -19:
						break;
					case 19:
						{return (new Token(Token.EXCLAMATION,yytext()));}
					case -20:
						break;
					case 20:
						{return (new Token(Token.PERCENT,yytext()));}
					case -21:
						break;
					case 21:
						{return (new Token(Token.SLASH,yytext()));}
					case -22:
						break;
					case 22:
						
					case -23:
						break;
					case 23:
						{return (new Token(Token.OR_OP,yytext()));}
					case -24:
						break;
					case 24:
						{return (new Token(Token.AND_OP,yytext()));}
					case -25:
						break;
					case 25:
						{return (new Token(Token.INCREMENT,yytext()));}
					case -26:
						break;
					case 26:
						{return (new Token(Token.DECREMENT,yytext()));}
					case -27:
						break;
					case 27:
						{return (new Token(Token.COLON_EQUAL,yytext()));}
					case -28:
						break;
					case 28:
						{return (new Token(Token.LESS_DASH,yytext()));}
					case -29:
						break;
					case 29:
						{return (new Token(Token.SHIFT_LEFT,yytext()));}
					case -30:
						break;
					case 30:
						{return (new Token(Token.SHIFT_RIGHT,yytext()));}
					case -31:
						break;
					case 31:
						{return (new Token(Token.FOR,yytext()));}
					case -32:
						break;
					case 32:
						{return (new Token(Token.FUNC,yytext()));}
					case -33:
						break;
					case 33:
						{return (new Token(Token.TYPE,yytext()));}
					case -34:
						break;
					case 34:
						{return (new Token(Token.BREAK,yytext()));}
					case -35:
						break;
					case 35:
						{return (new Token(Token.RETURN,yytext()));}
					case -36:
						break;
					case 36:
						{return (new Token(Token.CDOTS,yytext()));}
					case -37:
						break;
					case 38:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -38:
						break;
					case 40:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -39:
						break;
					case 42:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -40:
						break;
					case 44:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -41:
						break;
					case 46:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -42:
						break;
					case 48:
						{
  return new Token(Token.ERROR, "Invalid input: " + yytext());
}
					case -43:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
