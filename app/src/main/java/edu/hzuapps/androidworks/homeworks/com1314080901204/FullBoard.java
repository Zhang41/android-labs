package com.example.com1314080901204.board.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.com1314080901204.board.AbstractBoard;
import com.example.com1314080901204.object.GameConf;
import com.example.com1314080901204.view.Piece;

/**
 * �ճ���ά��������Χ�Ĳ��֣��������еĲ���
 * 
 * @author CJP
 * 
 */
public class FullBoard extends AbstractBoard {

	@Override
	protected List<Piece> createPieces(GameConf config, Piece[][] pieces) {
		List<Piece> notNullPieces = new ArrayList<Piece>();
		for (int i = 1; i < pieces.length - 1; i++) {
			for (int j = 1; j < pieces.length - 1; j++) {
				Piece piece = new Piece(i, j);
				notNullPieces.add(piece);
			}
		}
		return notNullPieces;
	}

}
